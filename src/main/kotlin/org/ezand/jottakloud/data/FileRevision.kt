package org.ezand.jottakloud.data

import org.joda.time.DateTime

data class FileRevision(
        val number: Int,
        val state: String,
        val created: DateTime,
        val modified: DateTime,
        val mime: String,
        val mstyle: String,
        val size: Long,
        val md5: String,
        val updated: DateTime
)
