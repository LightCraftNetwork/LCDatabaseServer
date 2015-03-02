package com.lightcraftmc.database.util;

import java.io.File;
import java.util.ArrayList;

import com.lightcraftmc.database.command.WebCommandInterpreter;

public class UtilCategoriesPage {

    public static ArrayList<String> getCategoriesPage(String[] args, String ip) {
        Banner banner = Banner.blank();
        if (args[0].contains("!")) {
            String create = args[0].split("!")[1];
            WebCommandInterpreter.getInstance().interpret("insert " + create + " temp Created_by_web_interface", ip, true);
            RawCategory r = getCategory(create);
            for (File f : r.getItems()) {
                if (!f.isDirectory()) {
                    f.delete();
                }
            }
            banner = Banner.defaultSuccess();
            args[0] = args[0].split("!")[0];
        }
        if (args.length == 1) {
            return categoriesMainPage(banner);
        }
        boolean categoriesPage = false;
        if (args.length > 2) {
            String command = args[2];
            if (command.equalsIgnoreCase("delete")) {
                try {
                    RawCategory r = getCategory(args[1]);
                    UtilDelete.deleteDirectory(r.getFile());
                    banner = Banner.defaultSuccess();
                    categoriesPage = true;
                } catch (Exception ex) {
                    banner = Banner.defaultFailed();
                }
            }
            if (command.equalsIgnoreCase("empty")) {
                try {
                    RawCategory r = getCategory(args[1]);
                    for (File f : r.getItems()) {
                        if (!f.isDirectory()) {
                            f.delete();
                        }
                    }
                    banner = Banner.defaultSuccess();
                } catch (Exception ex) {
                    banner = Banner.defaultFailed();
                }
            }
        }
        if (categoriesPage) {
            return categoriesMainPage(banner);
        }
        String category = args[1];
        RawCategory r = getCategory(category);
        return getCategoryPage(r, r != null, banner);
    }

    private static ArrayList<String> categoriesMainPage(Banner banner) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.addAll(UtilBootstrap.generateTitle("LCDatabaseServer listing"));
        lines.add(Tag.open("body"));
        lines.addAll(banner.getHTML());

        lines.addAll(UtilBootstrap.container("Category listing", "Lists all avalible categories", "Command line: list @a"));
        lines.addAll(UtilBootstrap.containerOpen());
        lines.addAll(UtilBootstrap.generateTabs("Categories"));
        lines.addAll(UtilBootstrap.containerClose());
        lines.addAll(UtilBootstrap.containerOpen());
        lines.add("<br>");
        lines.add("<form class=\"well span9\" method=\"GET\" action=\"/?categories!\">");
        lines.add("<div class=\"input-group input-group-lg\">");
        lines.add("<span class=\"input-group-addon\" id=\"sizing-addon1\">Create Category</span>");
        lines.add("<input type=\"text\" class=\"form-control\" placeholder=\"Title\" name=\"createcategory\" aria-describedby=\"sizing-addon1\">");
        lines.add("</div>");
        lines.add("</form>");
        lines.add("<br>");
        lines.addAll(UtilBootstrap.generateCategoryListing("Available Categories", UtilGenerateCategories.getCategories(), "/?categories-"));
        lines.addAll(UtilBootstrap.containerClose());
        lines.add(Tag.close("body"));
        lines.add(Tag.close("html"));
        return lines;
    }

    private static ArrayList<String> getCategoryPage(RawCategory category, boolean exists, Banner banner) {
        if (!exists || category == null) {
            if (banner.isShouldShow()) {
                return categoriesMainPage(banner);
            } else {
                return categoriesMainPage(new Banner("That category could not be found!", "Error:", ButtonType.DANGER, true));
            }

        }
        String pfx = "/?categories-" + category.getName() + "-";
        ArrayList<String> lines = new ArrayList<String>();
        lines.addAll(UtilBootstrap.generateTitle("LCDatabaseServer listing"));
        lines.add(Tag.open("body"));
        lines.addAll(banner.getHTML());
        lines.addAll(UtilBootstrap.container("Listing of " + category.getName(), "", "Command line: list " + category.getName()));
        lines.addAll(UtilBootstrap.containerOpen());
        lines.addAll(UtilBootstrap.createSmallButton("< Go back", "/?categories", "info"));
        ArrayList<Link> dropdown = new ArrayList<Link>();
        ArrayList<Link> dropdown2 = new ArrayList<Link>();
        // dropdown.add(new Link(pfx + "allowpublic", "Allow Public Access"));
        dropdown2.add(new Link(pfx, "Are you sure?"));
        dropdown2.add(new Link(pfx, "<b>This will also remove sub-categories!</b>"));
        dropdown2.add(Link.seperator());
        dropdown2.add(new Link(pfx + "delete", "Yes, I'm sure!"));
        dropdown2.add(new Link(pfx + "empty", "No, just empty!"));
        dropdown2.add(new Link(pfx, "No, just close!"));
        dropdown.add(new Link(pfx + "empty", "Empty"));
        lines.addAll(UtilBootstrap.generateDropdown("Actions", ButtonType.SUCCESS, dropdown));
        lines.addAll(UtilBootstrap.generateDropdown("Delete", ButtonType.DANGER, dropdown2));
        lines.addAll(UtilBootstrap.containerClose());
        lines.add("<br>");
        try{
        lines.addAll(UtilBootstrap.containerOpen());
        lines.addAll(UtilBootstrap.generateTable(category));
        lines.addAll(UtilBootstrap.containerClose());
        lines.addAll(UtilBootstrap.containerOpen());
        lines.addAll(UtilBootstrap.generateSubcategoriesTable(category));
        lines.addAll(UtilBootstrap.containerClose());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        lines.add(Tag.close("body"));
        lines.add(Tag.close("html"));
        return lines;
    }

    private static RawCategory getCategory(String category) {
        for (RawCategory r : UtilGenerateCategories.getCategories()) {
            if (r.getName().equals(category.replace("/", "\\"))) {
                return r;
            }
        }
        return null;
    }

}
