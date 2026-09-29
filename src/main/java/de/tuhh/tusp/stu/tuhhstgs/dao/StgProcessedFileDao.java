package de.tuhh.tusp.stu.tuhhstgs.dao;

import de.tuhh.tusp.stu.tuhhstgs.pojo.StgProcessedFile;
import de.tuhh.tusp.utils.BaseDao;

public interface StgProcessedFileDao extends BaseDao<StgProcessedFile> {

	StgProcessedFile fetchByInName(String name);

}
