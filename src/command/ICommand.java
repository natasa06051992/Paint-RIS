package command;

public interface ICommand {
    void execute();
    void unexecute();
    String getNameOfClass();
}
