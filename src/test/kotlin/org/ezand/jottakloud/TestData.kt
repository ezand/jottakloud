package org.ezand.jottakloud

import org.ezand.jottakloud.data.Device
import org.ezand.jottakloud.data.MountPoint
import org.ezand.jottakloud.deserializers.JottacloudDateTimeDeserializer
import java.util.*

object TestData {
    val dateTimeFormat = JottacloudDateTimeDeserializer().format

    val deviceXml = javaClass.getResourceAsStream("/jottacloud-responses/device.xml").bufferedReader().use { it.readText() }

    val device = Device(
            "Jotta",
            "Jotta",
            "JOTTA",
            UUID.fromString("a37f4dd6-3a47-4a9c-a70e-6c1410447427"),
            5365591317L,
            dateTimeFormat.parseDateTime("2017-01-22-T20:30:24Z"),
            "someUser",
            listOf(
                    MountPoint("Archive", 0L, null, null, null, null, null, null, null),
                    MountPoint("Photos", 5365394415L, dateTimeFormat.parseDateTime("2017-01-22-T20:30:24Z"), null, null, null, null, null, null),
                    MountPoint("Shared", 0L, null, null, null, null, null, null, null),
                    MountPoint("Sync", 196902L, dateTimeFormat.parseDateTime("2017-01-20-T13:47:22Z"), null, null, null, null, null, null)
                    ))
}