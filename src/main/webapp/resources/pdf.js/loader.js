// If absolute URL from the remote server is provided, configure the CORS
// header on that server.
var url = document.getElementById('url').innerText;


// Disable workers to avoid yet another cross-origin issue (workers need
// the URL of the script to be loaded, and dynamically loading a cross-origin
// script does not work).
// PDFJS.disableWorker = true;

// The workerSrc property shall be specified.
var scripts = document.getElementsByTagName("script");
src = scripts[scripts.length-1].src;
prefix = src.substring(0, src.lastIndexOf('\/'));
PDFJS.workerSrc = prefix + '/pdf.worker.js';

var pdfDoc = null;
var pageNum = 1;
var pageRendering = false;
var pageNumPending = null;
var scale = 1.0;
var canvas = document.getElementById('the-canvas');
var wrapper = document.getElementById('wrapper');
var ctx = canvas.getContext('2d');
var transform = 0;

/**
 * Get page info from document, resize canvas accordingly, and render page.
 * 
 * @param num
 *            Page number.
 */
function renderPage(num) {
	pageRendering = true;
	// Using promise to fetch the page
	pdfDoc.getPage(num).then(function(page) {
		var viewport = page.getViewport(parseFloat(scale) + 0.5);
		canvas.height = viewport.height;
		canvas.width = viewport.width;
		
		canvas.style.width = viewport.width+'px';
		canvas.style.height = viewport.height+'px';

		var viewport = page.getViewport((parseFloat(scale) + 0.5) * 2);
		canvas.height = viewport.height;
		canvas.width = viewport.width;
		
		// Render PDF page into canvas context
		var renderContext = {
			canvasContext : ctx,
			viewport : viewport
		};
		var renderTask = page.render(renderContext);
		rotate();

		// Wait for rendering to finish
		renderTask.promise.then(function() {
			pageRendering = false;
			if (pageNumPending !== null) {
				// New page rendering is pending
				renderPage(pageNumPending);
				pageNumPending = null;
			}
		});
	});

	// Update page counters
	document.getElementById('page_num').textContent = pageNum;
}

/**
 * If another page rendering in progress, waits until the rendering is finised.
 * Otherwise, executes rendering immediately.
 */
function queueRenderPage(num) {
	if (pageRendering) {
		pageNumPending = num;
	} else {
		renderPage(num);
	}
}

/**
 * Displays previous page.
 */
function onPrevPage() {
	if (pageNum <= 1) {
		return;
	}
	pageNum--;
	queueRenderPage(pageNum);
}
document.getElementById('prev').addEventListener('click', onPrevPage);

/**
 * Displays next page.
 */
function onNextPage() {
	if (pageNum >= pdfDoc.numPages) {
		return;
	}
	pageNum++;
	queueRenderPage(pageNum);
}
document.getElementById('next').addEventListener('click', onNextPage);

/**
 * Change scale.
 */
function onChangeScale() {
	scale = document.getElementById('scale').value;
	queueRenderPage(pageNum);
}
document.getElementById('scale').addEventListener('change', onChangeScale);

/**
 * Rotate Right
 */
function onRotateRight() {
	transform += 90;
	rotate();
}
document.getElementById('rotate-right').addEventListener('click', onRotateRight);

/**
 * Rotate Left
 */
function onRotateLeft() {
	transform -= 90;
	rotate();
}
document.getElementById('rotate-left').addEventListener('click', onRotateLeft);

function rotate() {
	shadowOffset = 10;
	menuOffset = menu.offsetTop + shadowOffset;
	canvasHeight = 0;
	if(transform % 180) {
		height = parseInt(canvas.style.height, 10);
		width = parseInt(canvas.style.width, 10);
		if(height > width) {
			canvas.style.left = (Math.abs(height - width) / 2 + shadowOffset) + 'px';
			canvas.style.top = (Math.abs(width - height) / 2 * -1 + menuOffset + shadowOffset) + 'px';
		} else {
			canvas.style.left = (Math.abs(height - width) / -2 + shadowOffset) + 'px';
			canvas.style.top = (Math.abs(width - height) / -2 * -1 + menuOffset + shadowOffset) + 'px';
		}
		canvasHeight = parseInt(canvas.style.width, 10);
	} else {
		canvas.style.left = (shadowOffset) + 'px';
		canvas.style.top = (menuOffset + shadowOffset) + 'px';
		canvasHeight = parseInt(canvas.style.height, 10);
	}
	wrapper.style.height = canvasHeight + 2 * shadowOffset + menuOffset + 40;
	console.log(wrapper.style.height);
	console.log(canvas.style.left);
	console.log(canvas.style.top);
	canvas.style.transform = "rotate(" + transform + "deg)";
}

/**
 * Asynchronously downloads PDF.
 */
PDFJS.getDocument(url).then(function(pdfDoc_) {
	pdfDoc = pdfDoc_;
	document.getElementById('page_count').textContent = pdfDoc.numPages;

	// Initial/first page rendering
	renderPage(pageNum);
});