package org.ezand.jottakloud.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class JottacloudDateTimeDeserializer : StdDeserializer<DateTime>(DateTime::class.java) {
    private val format = DateTimeFormat.forPattern("yyyy-MM-dd-'T'HH:mm:ss'Z'")

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): DateTime {
        return format.parseDateTime(p?.valueAsString)
    }
}
