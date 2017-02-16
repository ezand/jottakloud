package org.ezand.jottakloud.data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Folder(@JacksonXmlProperty(isAttribute = true) val name: String)
