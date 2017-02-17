package org.ezand.jottakloud

import org.wasabi.app.AppConfiguration
import org.wasabi.app.AppServer
import java.net.ServerSocket

class TestWebServer : AppServer(AppConfiguration(port = ServerSocket(0).use { it.localPort }))
