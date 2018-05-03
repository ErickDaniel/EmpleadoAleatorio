package basicsolutionsoftware.com.empleadoaleatorio.Domain.Manager;

import basicsolutionsoftware.com.empleadoaleatorio.Domain.TaskInterface.Task;

public class TaskManager {

    private static TaskManager taskManager;

    public TaskManager(){
    }

    public void execute(Task task){
        task.execute();
    }

}
