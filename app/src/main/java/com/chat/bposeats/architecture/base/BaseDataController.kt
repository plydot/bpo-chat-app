package com.chat.bposeats.architecture.base

import com.chat.bposeats.data.data.dao.DaoFactory

open class BaseDataController(daoFactory: DaoFactory) : BaseContract.DataController {

    internal var dao: DaoFactory = daoFactory

}