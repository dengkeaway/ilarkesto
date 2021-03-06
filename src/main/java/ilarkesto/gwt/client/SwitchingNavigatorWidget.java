package ilarkesto.gwt.client;

import ilarkesto.core.menu.StaticMenu;
import ilarkesto.core.menu.StaticMenuItem;
import ilarkesto.core.menu.StaticSubmenu;
import ilarkesto.core.menu.Submenu;

import com.google.gwt.user.client.ui.Widget;

public class SwitchingNavigatorWidget extends NavigatorWidget<Object> {

	private SwitcherWidget switcher;

	public SwitchingNavigatorWidget(SwitcherWidget switcher) {
		assert switcher != null;
		this.switcher = switcher;
	}

	public void addGroup(String label, String key) {
		final StaticSubmenu submenu = new StaticSubmenu(label);
		submenu.setPayload(key);
		menu.addItem(submenu);
	}

	public void addItem(String group, String label, final Widget widget) {
		final Submenu<StaticMenuItem> groupItem = (Submenu<StaticMenuItem>) menu.getItemByPayload(group);
		assert groupItem != null;
		StaticMenu menu = (StaticMenu) groupItem.getMenu();
		StaticMenuItem subItem = menu.addItem(new StaticMenuItem(label));
		subItem.setPayload(widget);
		subItem.setOnSelect(new Runnable() {

			@Override
			public void run() {
				groupItem.select();
				showItem(widget);
			}

		});
	}

	public void addItem(String label, final Widget widget) {
		assert label != null;
		assert widget != null;
		addItem(label, widget, new Runnable() {

			@Override
			public void run() {
				showItem(widget);
			}

		});
	}

	public SwitchAction createSwitchAction(Widget widget) {
		return new SwitchAction(widget);
	}

	protected void showItem(final Widget widget) {
		switcher.show(widget);
	}

	public class SwitchAction extends AAction {

		private Widget widget;
		private String label;

		public SwitchAction(Widget widget) {
			this.widget = widget;
		}

		@Override
		public String getLabel() {
			if (label != null) return label;
			StaticMenuItem item = menu.getItemByPayload(widget);
			return item.getLabel();
		}

		@Override
		protected void onExecute() {
			select(widget);
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}
}
