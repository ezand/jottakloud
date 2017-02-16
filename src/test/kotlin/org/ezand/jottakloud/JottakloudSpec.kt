package org.ezand.jottakloud

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.ezand.jottakloud.data.Device
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.mockito.ArgumentMatchers.anyString
import java.util.*

// TODO real tests
class JottakloudSpec : Spek({
    val jottacloud = mock<Jottacloud> {
        on { getDevice(anyString()) } doReturn Device("FOOO", "BAR", "SNA", UUID.randomUUID(), 1L, null, null, emptyList())
    }

    describe("FOO") {
        it("BAR") {
            val device = jottacloud.getDevice("foo")
            println(device)
        }
    }
})