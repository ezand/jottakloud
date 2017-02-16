package org.ezand.jottakloud.data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class File(
        @JacksonXmlProperty(isAttribute = true) val name: String
)
