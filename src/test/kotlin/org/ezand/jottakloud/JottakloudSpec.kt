package org.ezand.jottakloud

import org.ezand.jottakloud.TestData.device
import org.ezand.jottakloud.TestData.deviceXml
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.net.URL
import kotlin.test.assertEquals

class JottakloudSpec : Spek({
    val server = TestWebServer()
    server.get("/someUser/Jottacloud", { response.send(deviceXml) })

    val jottakloud = Jottacloud(JottacloudAuthentication("someUser", "password"), URL("http://127.0.0.1:${server.configuration.port}"))

    describe("Fetching Jottacloud content") {
        beforeGroup { server.start(wait = false) }
        afterGroup { server.stop() }

        it("should return correct device") {
            val actual = jottakloud.getDevice("Jottacloud")
            assertEquals(device, actual)
        }
    }
})