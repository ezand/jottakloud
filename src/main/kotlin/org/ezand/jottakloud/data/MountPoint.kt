package org.ezand.jottakloud.data

import com.fasterxml.jackson.annotation.JsonFormat
import org.joda.time.DateTime

data class MountPoint(val name: String,
                      val size: Long,
                      val modified: DateTime?,
                      val path: String?,
                      val abspath: String?,
                      val device: String?,
                      val user: String?,
                      val files: List<File>?,
                      val folders: List<Folder>?)
