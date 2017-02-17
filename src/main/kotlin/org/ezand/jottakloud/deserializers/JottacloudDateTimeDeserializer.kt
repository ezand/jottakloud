package org.ezand.jottakloud.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class JottacloudDateTimeDeserializer : StdDeserializer<DateTime>(DateTime::class.java) {
     val format: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd-'T'HH:mm:ss'Z'")

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): DateTime {
        return format.parseDateTime(p?.valueAsString)
    }
}
