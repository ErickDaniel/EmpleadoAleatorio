package basicsolutionsoftware.com.datosaleatorios.Domain.Manager;

import basicsolutionsoftware.com.datosaleatorios.Domain.TaskInterface.Task;

public class TaskManager {

    private static TaskManager taskManager;

    public TaskManager(){
    }

    public void execute(Task task){
        task.execute();
    }

}
