package org.ezand.jottakloud.data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import org.joda.time.DateTime
import java.util.*

data class Device(val name: String,
                  @JacksonXmlProperty(localName = "display_name") val displayName: String,
                  val type: String,
                  val sid: UUID,
                  val size: Long,
                  val modified: DateTime?,
                  val user: String?,
                  @JacksonXmlProperty(localName = "mountPoints") val mountPoints: List<MountPoint>?)
