package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandManager {
    private static CommandManager instance = null;
    private QueueStack<List<ICommand>> queueStackNormal;
    private QueueStack<List<ICommand>> queueStackReverse;

    private List<String> actionHistory;

    public static CommandManager getInstance(){
        if(instance != null)
            return instance;
        return new CommandManager();
    }

    private CommandManager() {
        queueStackNormal = new QueueStack<>();
        queueStackReverse = new QueueStack<>();
        actionHistory = new ArrayList<>();
    }

    public void execute(List<ICommand> actionList){
        actionList.forEach(ICommand::execute);
        queueStackNormal.push(actionList);
        actionList.forEach(a -> actionHistory.add(a.getNameOfClass()));
    }

    public void undo() {
        Optional<List<ICommand>> optionalActions = queueStackNormal.pop();
        optionalActions.ifPresent(aList -> {
            aList.forEach(ICommand::undo);
            queueStackReverse.push(aList);
            aList.forEach(a -> actionHistory.add(a.getNameOfClass() + " - undo"));
        });
    }

    public void redo() {
        Optional<List<ICommand>> optionalActions = queueStackReverse.pop();
        optionalActions.ifPresent(aList -> {
            aList.forEach(ICommand::execute);
            queueStackNormal.push(aList);
            aList.forEach(a -> actionHistory.add(a.getNameOfClass() + " - redo"));
        });
    }

    public void clearNormal() {
        queueStackNormal.clear();
    }

    public void clearReverse() {
        queueStackReverse.clear();
    }

    public List<String> getActionHistory() {
        return actionHistory;
    }

}
