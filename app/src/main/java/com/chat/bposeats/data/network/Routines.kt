package com.chat.bposeats.data.network

import com.plydot.sms.bulksms.webservice.HttpService


class Routines() {
    private var service: ApiInterface? = null

    init {
        setService()
    }

    private fun setService() {
        service = HttpService.service()
    }
}