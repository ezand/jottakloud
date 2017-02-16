package org.ezand.jottakloud.data

data class User(val username: String,
                val accountType: String,
                val locked: Boolean,
                val capacity: Long,
                val maxDevices: Int,
                val maxMobileDevices: Int,
                val usage: Long,
                val readLocked: Boolean,
                val writeLocked: Boolean,
                val quotaWriteLocked: Boolean,
                val enableSync: Boolean,
                val enableFoldershare: Boolean,
                val devices: List<Device>?)
