package br.com.project.fretes

import br.com.project.CalculaFreteRequest
import br.com.project.CalculaFreteResponse
import br.com.project.FretesServiceGrpc
import io.grpc.stub.StreamObserver
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import kotlin.random.Random

@Singleton
class FretesGrpcServer: FretesServiceGrpc.FretesServiceImplBase() {
    //classe de serviço para calcular o frete. O cliente foi o bloomRPC
    private val logger = LoggerFactory.getLogger(FretesGrpcServer::class.java)
    override fun calculaFrete(request: CalculaFreteRequest?, responseObserver: StreamObserver<CalculaFreteResponse>?) {
        logger.info("Calculando frete para reqeust: $request")

        val response = CalculaFreteResponse.newBuilder()
            .setCep(request!!.cep)
            .setValor(Random.nextDouble(0.0, until = 140.0))
            .build()

        logger.info("Frete calculado $response")
        responseObserver!!.onNext(response)
        responseObserver.onCompleted()
    }
}