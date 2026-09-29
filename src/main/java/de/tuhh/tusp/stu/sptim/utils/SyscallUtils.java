package de.tuhh.tusp.stu.sptim.utils;

import java.io.File;
import java.io.IOException;

import de.tuhh.tusp.stu.sptim.utils.SyscallUtilsImpl.CmdFaildException;

public interface SyscallUtils {

	/**
	 * Fürht ein Programm aus
	 *
	 * @param cmd
	 * @return Programoutput
	 * @throws IOException
	 *             Wenn das Programm nicht zugreifbar ist
	 * @throws InterruptedException
	 *             Wenn das Programm abgebrochen wird
	 * @throws CmdFaildException
	 *             Wenn ein Returncode != 0 kommt
	 */
	String runCmd(File workdir, String... cmd) throws IOException, InterruptedException, CmdFaildException;
}
