package command;

/* *
 * * Interface ICommand that have methods for executing, undoed commands
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public interface ICommand {
    /**
     * Executes command
     */
    void execute();

    /**
     * Undoes command
     */
    void undo();

    /**
     * Sets name of command and returns it
     * @return name of command
     */
    String getNameOfCommand();
}
