package ir.tamin.teco.application.port.in;

public interface OrderEventHandler {
    void handle(/*KAFKA EVENT MODEL or JSON STRING*/);
}
