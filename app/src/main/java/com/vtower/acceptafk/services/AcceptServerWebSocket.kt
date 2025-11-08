package com.vtower.acceptafk.services

import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import com.microsoft.signalr.TransportEnum

object AcceptServerWebSocket {

    private var hubConnection: HubConnection? = null

    fun startConnection(serverIp: String) {
        try {
            val url = "http://$serverIp:5000/chatHub"

            hubConnection = HubConnectionBuilder
                .create(url)
                .withTransport(TransportEnum.WEBSOCKETS)
                .build()

            // Evento quando a conexão é fechada
            hubConnection?.onClosed { error ->
                if (error != null) {
                    println("❌ Conexão encerrada por erro: ${error.message}")
                } else {
                    println("🔌 Conexão encerrada normalmente.")
                }

                // Exemplo: tentar reconectar automaticamente
                reconnectWithDelay()
            }

            // Registrar eventos recebidos
            hubConnection?.on("ReceiveMessage", { user: String, message: String ->
                println("Mensagem recebida de $user: $message")
            }, String::class.java, String::class.java)

            // Iniciar conexão
            hubConnection?.start()?.doOnComplete {
                println("✅ Conectado ao servidor SignalR!")
            }?.doOnError {
                println("❌ Erro na conexão: ${it.message}")
            }?.subscribe()

        } catch (e: Exception) {
            println("################### Erro tentar abrir conexão com o servidor:  ${e.message}")
        }

    }

    private fun reconnectWithDelay() {
        Thread {
            try {
                println("⏳ Tentando reconectar em 5 segundos...")
                Thread.sleep(5000)
                hubConnection?.start()
                    ?.doOnComplete {
                        println("🔁 Reconectado com sucesso!")
                    }
                    ?.doOnError {
                        println("❌ Falha ao reconectar: ${it.message}")
                        reconnectWithDelay() // tenta novamente
                    }
                    ?.subscribe()
            } catch (e: Exception) {
                println("⚠️ Erro no processo de reconexão: ${e.message}")
            }
        }.start()
    }

    fun sendMessage(user: String, message: String) {

        println("Status da conexão: ${hubConnection?.connectionState}")

        if (hubConnection?.connectionState != HubConnectionState.CONNECTED)
            println("❌ Conexão não estabelecida")
        else
            hubConnection?.send("SendMessage", user, message)
    }

    fun stopConnection() {
        hubConnection?.stop()
    }
}