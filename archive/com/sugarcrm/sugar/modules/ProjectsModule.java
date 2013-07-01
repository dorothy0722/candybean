/**
 * ====
 *     Candybean is a next generation automation and testing framework suite.
 *     It is a collection of components that foster test automation, execution
 *     configuration, data abstraction, results illustration, tag-based execution,
 *     top-down and bottom-up batches, mobile variants, test translation across
 *     languages, plain-language testing, and web service testing.
 *     Copyright (C) 2013 <candybean@sugarcrm.com>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * ====
 *
 * Candybean is a next generation automation and testing framework suite.
 * It is a collection of components that foster test automation, execution
 * configuration, data abstraction, results illustration, tag-based execution,
 * top-down and bottom-up batches, mobile variants, test translation across
 * languages, plain-language testing, and web service testing.
 * Copyright (C) 2013 SugarCRM, Inc. <candybean@sugarcrm.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sugarcrm.sugar.modules;

import com.sugarcrm.sugar.Sugar;
import com.sugarcrm.voodoo.automation.control.VHook.Strategy;

/**
 * @author Conrad Warmbold
 *
 */
public class ProjectsModule {
	
	private final Sugar sugar;
	
	public ProjectsModule(Sugar sugar) { this.sugar = sugar; }
	
	public void createProject(ProjectRecord project) throws Exception {
		String sugarURL = sugar.config.getProperty("env.base_url", "http://localhost/ent670/");
		sugar.i.go(sugarURL + "/index.php?module=Project&action=EditView");
		sugar.i.getControl(Strategy.ID, "name").sendString(project.name);
		sugar.i.getControl(Strategy.ID, "estimated_start_date").sendString(project.startDate);
		sugar.i.getControl(Strategy.ID, "estimated_end_date").sendString(project.endDate);
		sugar.i.getControl(Strategy.ID, "SAVE_HEADER").click();
		sugar.i.getControl(Strategy.ID, "moduleTab_AllHome").click();
	}
	
	public void deleteAllProjects() throws Exception {
		String sugarURL = sugar.config.getProperty("env.base_url", "http://localhost/ent670/");
		sugar.i.go(sugarURL + "/index.php?module=Project&action=ListView");
		sugar.i.getControl(Strategy.ID, "massall_top").click();
		sugar.i.getControl(Strategy.ID, "delete_listview_top").click();
		sugar.i.acceptDialog();
		sugar.i.getControl(Strategy.ID, "moduleTab_AllHome").click();
	}
	
	public void editProject(ProjectRecord oldProject, ProjectRecord newProject) throws Exception {
		this.searchProjects(oldProject.name);
		sugar.i.getControl(Strategy.PLINK, oldProject.name).click();
		sugar.i.getControl(Strategy.ID, "edit_button").click();
		sugar.i.getControl(Strategy.ID, "name").sendString(newProject.name);
		sugar.i.getControl(Strategy.ID, "estimated_start_date").sendString(newProject.startDate);
		sugar.i.getControl(Strategy.ID, "estimated_end_date").sendString(newProject.endDate);
		sugar.i.getControl(Strategy.ID, "SAVE_HEADER").click();
		sugar.i.getControl(Strategy.ID, "moduleTab_AllHome").click();
	}
	
	public void searchProjects(String search) throws Exception {
		String sugarURL = sugar.config.getProperty("env.base_url", "http://localhost/ent670/");
		sugar.i.go(sugarURL + "/index.php?module=Project&action=ListView");
		sugar.i.getControl(Strategy.ID, "name_basic").sendString(search);
		sugar.i.getControl(Strategy.ID, "search_form_submit").click();
	}
	
	public static class ProjectRecord {
		
		public String name = null;
		public String startDate = null;
		public String endDate = null;

		public ProjectRecord(String name, String startDate, String endDate) {
			this.name = name;
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}
}