package de.jenshardt.vaadindemo.ui;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import de.jenshardt.vaadindemo.model.Customer;
import de.jenshardt.vaadindemo.repo.CustomerRepository;

@Route
public class MainView extends VerticalLayout {

	private static final long serialVersionUID = -3950204484065390829L;
	private final CustomerRepository repo;

	private final CustomerEditor editor;

	private final Grid<Customer> grid;
	private final TextField filter;
	private final Button addNewBtn;
	private final ConfirmationDialog dialog;

	public MainView(CustomerRepository repo, CustomerEditor editor) {
		this.repo = repo;
		this.editor = editor;
		this.grid = new Grid<>(Customer.class);
		this.filter = new TextField();
		this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());
		this.dialog = new ConfirmationDialog();

		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		add(actions, grid, editor);

		grid.setColumns("id", "firstName", "lastName");
		grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
		grid.addComponentColumn(item -> new Button("Delete", VaadinIcon.TRASH.create(), click -> {
			dialog.confirmAndDelete(item);
			listCustomers(filter.getValue());
		}));
		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editCustomer(e.getValue());
		});
		
		filter.setPlaceholder("Filter by last name");
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listCustomers(e.getValue()));

		addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "")));
	
		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listCustomers(filter.getValue());
		});

		// Initialize Customer List
		listCustomers(null);
	}

	void listCustomers(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repo.findAll());
		}
		else {
			grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
		}
	}
}