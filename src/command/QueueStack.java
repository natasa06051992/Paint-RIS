package command;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
/* *
 * * The QueueStack is generic collection
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
class QueueStack<T> {

    private List<T> dataCollection;
    QueueStack() {
        dataCollection = new LinkedList<>();
    }

    /**
     * This method pushes item on collection
     * @param item Item that is added to collection
     */
    void push(T item) {
        dataCollection.add(item);
    }

    /**
     * This method removes last item in collection
     * @return item form collection that is last added
     */
    Optional<T> pop() {
        if(dataCollection.size() > 0)
            return Optional.of(dataCollection.remove(dataCollection.size() - 1));
        else
            return Optional.empty();
    }
}
