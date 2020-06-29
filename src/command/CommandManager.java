package command;

import view.Draw;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/* *
 * * The CommandManager class that manages commands
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class CommandManager {
    private static CommandManager instance = null;
    private QueueStack<List<ICommand>> queueStackNormal;
    private QueueStack<List<ICommand>> queueStackReverse;
    public final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Singleton of CommandManager
     * @return Instance of CommandManager
     */
    public static CommandManager getInstance(){
        if(instance != null)
            return instance;
        return new CommandManager();
    }

    /**
     * Private constructor
     */
    private CommandManager() {
        queueStackNormal = new QueueStack<>();
        queueStackReverse = new QueueStack<>();
    }

    /**
     * Executes commands
     * @param actionList Action list of all commands that should be executed
     */
    public void execute(List<ICommand> actionList){
        actionList.forEach(ICommand::execute);
        queueStackNormal.push(actionList);
        actionList.forEach(a ->logger.log(Level.FINE, a.getNameOfCommand()));

        for (var action:actionList) {
            draw.getTxtInfo().append(action.getNameOfCommand()+'\n');
        }
    }

    /**
     * Undoes last command
     */
    public void undo() {
        Optional<List<ICommand>> optionalActions = queueStackNormal.pop();
        optionalActions.ifPresent(aList -> {
            aList.forEach(ICommand::undo);
            queueStackReverse.push(aList);
            for (var action:aList) {
                draw.getTxtInfo().append(action.getNameOfCommand()+" Undo" +'\n');
            }
            aList.forEach(a ->logger.log(Level.FINE, a.getNameOfCommand()+" Undo" ));
        });
    }

    /**
     * Redo of last retrieved command
     */
    public void redo() {
        Optional<List<ICommand>> optionalActions = queueStackReverse.pop();
        optionalActions.ifPresent(aList -> {
            aList.forEach(ICommand::execute);
            queueStackNormal.push(aList);
            aList.forEach(a ->logger.log(Level.FINE, a.getNameOfCommand()+" Redo"));

            for (var action:aList) {
                draw.getTxtInfo().append(action.getNameOfCommand()+" Redo" +'\n');
            }
        });
    }


    Draw draw;

    /**
     * Sets Draw field
     * @param draw
     */
    public void setDraw(Draw draw) {
        this.draw=draw;
    }
}
