package de.jenshardt.vaadindemo.ui;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import de.jenshardt.vaadindemo.model.Customer;

public class ConfirmationDialog extends ConfirmDialog {

	private Customer customer;
	
	private ConfirmDialog dialog;
	
	public ConfirmationDialog() {}

	public void confirmAndDelete(Customer item) {
		dialog = new ConfirmDialog();
		
	}
	
	private void onDelete() {
		
	}
	private void onCancel() {
		
	}
	
}