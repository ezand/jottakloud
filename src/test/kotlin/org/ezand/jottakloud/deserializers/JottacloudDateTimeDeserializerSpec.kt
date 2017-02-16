package org.ezand.jottakloud.deserializers

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.joda.time.DateTime
import kotlin.test.assertEquals

class JottacloudDateTimeDeserializerSpec : Spek({
    val mapper = XmlMapper()
            .registerModule(JodaModule().addDeserializer(DateTime::class.java, JottacloudDateTimeDeserializer()))

    describe("A date in string-format") {
        val dateString = "2017-02-01-T01:02:33Z"

        it("should return the correct DateTime") {
            val date = mapper.convertValue(dateString, DateTime::class.java)

            assertEquals(2017, date.year)
            assertEquals(2, date.monthOfYear)
            assertEquals(1, date.dayOfMonth)
            assertEquals(1, date.hourOfDay)
            assertEquals(2, date.minuteOfHour)
            assertEquals(33, date.secondOfMinute)
        }
    }
})