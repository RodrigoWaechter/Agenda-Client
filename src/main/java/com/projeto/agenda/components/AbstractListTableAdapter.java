package com.projeto.agenda.components;

import java.util.List;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.list.SelectionInList;

@SuppressWarnings("serial")
public abstract class AbstractListTableAdapter<T> extends AbstractTableAdapter<T> {
	private SelectionInList<T> model;
	private List<T> list;
	private static final String[] EMPTY_ITEM_ARRAY = new String[] {""};
	
	public AbstractListTableAdapter(SelectionInList<T> model, String[] columns) {
		super(model, (columns == null || columns.length == 0 ? EMPTY_ITEM_ARRAY : columns));
		this.model = model;
	}
	
	public void setData(List<T> list) {
		if (model == null) return;
		this.list = list;
		model.setList(list);
		fireTableDataChanged();
	}
	
	public void clearData() {
		if (this.list == null) return;
		this.list.clear();
	}
	
	public List<T> getData(){
		return model.getList();
	}
}