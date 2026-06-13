package ir.tamin.teco.application.model.command;

public final class UserCommand {

    public record create(String name, String family) {}
    public record update(long id,String name, String family){}
    public record delete(long id){}

}
