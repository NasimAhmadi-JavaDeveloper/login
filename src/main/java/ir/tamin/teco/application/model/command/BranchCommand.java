package ir.tamin.teco.application.model.command;

public record BranchCommand() {

    public record create(String brhCode) { }

    public record update(long id, String name, String family) { }

    public record delete(long id) { }

}
