package de.tuhh.tusp.stu.tuhhstgs.service;

import org.springframework.core.io.WritableResource;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.utils.MessageCollector;

public interface StgService {

	WritableResource exportStgs(MessageCollector collector, JobLogger jobLogger);

}
