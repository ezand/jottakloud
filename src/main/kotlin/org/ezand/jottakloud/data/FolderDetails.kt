package org.ezand.jottakloud.data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import org.joda.time.DateTime

data class FolderDetails(
        val path: String,
        val abspath: String,
        val time: DateTime,
        @JacksonXmlProperty(isAttribute = true) val name: String,
        val metadata: FolderMetadata,
        val folders: List<Folder> = emptyList(),
        val files: List<File> = emptyList()
)
