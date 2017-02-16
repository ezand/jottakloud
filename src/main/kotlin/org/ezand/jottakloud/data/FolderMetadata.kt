package org.ezand.jottakloud.data

import com.fasterxml.jackson.annotation.JsonProperty

data class FolderMetadata(val total: Int?,
                          @JsonProperty("num_folders") val numFolders: Int?,
                          @JsonProperty("num_files") val numFiles: Int?)
