package gui.mvp.undoredoquiz.main;

import java.util.ArrayList;
import java.util.List;

public class UndoRedoManager {
	
	    private List<UndoableRedoableAction> actions;
	    private int currentPosition;

	    public UndoRedoManager()
	    {
	        actions = new ArrayList<>();
	        currentPosition = 0;
	    }

	    public void addAction(UndoableRedoableAction action)
	    {
	        for(int i = actions.size() - 1; i >= currentPosition; i--)
	        {
	            actions.remove(i);
	        }
	        actions.add(action);
	        currentPosition++;
	    }
	    
	    public void undo()
	    {
	        if(currentPosition > 0)
	        {
	            currentPosition--;
	            actions.get(currentPosition).undo();
	        }
	    }
	    
	    public void redo()
	    {
	        if(currentPosition < actions.size())
	        {
	            actions.get(currentPosition).redo();
	            currentPosition++;
	        }
	    }
	    
	    public boolean canUndo() {
	    	return actions.size() > 0 && currentPosition > 0;
	    }
	    
	    public boolean canRedo() {
	    	return currentPosition < actions.size();
	    }
	    
	   
}

class SampleAction implements UndoableRedoableAction {
	    private String name;

	    public SampleAction(String name)
	    {
	        super();
	        this.name = name;
	    }

	    public void undo()
	    {
	        System.out.println("undo " + name);
	    }

	    public void redo()
	    {
	        System.out.println("redo " + name);
	    }
	    
}
