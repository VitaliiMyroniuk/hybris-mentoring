package org.training.myextension.hmc;

import de.hybris.platform.hmc.AbstractEditorMenuChip;
import de.hybris.platform.hmc.AbstractExplorerMenuTreeNodeChip;
import de.hybris.platform.hmc.EditorTabChip;
import de.hybris.platform.hmc.extension.HMCExtension;
import de.hybris.platform.hmc.extension.MenuEntrySlotEntry;
import de.hybris.platform.hmc.generic.ClipChip;
import de.hybris.platform.hmc.generic.ToolbarActionChip;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.hmc.webchips.DisplayState;
import java.util.*;

public class MyextensionHMCExtension extends HMCExtension {

    /** Path to the resource bundles. */
    public final static String RESOURCE_PATH = "org.training.myextension.hmc.locales";

    @Override
    public List<EditorTabChip> getEditorTabChips(DisplayState displayState, AbstractEditorMenuChip abstractEditorMenuChip) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<AbstractExplorerMenuTreeNodeChip> getTreeNodeChips(DisplayState displayState, Chip chip) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<MenuEntrySlotEntry> getMenuEntrySlotEntries(DisplayState displayState, Chip chip) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ClipChip> getSectionChips(DisplayState displayState, ClipChip clipChip) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ToolbarActionChip> getToolbarActionChips(DisplayState displayState, Chip chip) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public ResourceBundle getLocalizeResourceBundle(Locale locale) throws MissingResourceException {
        return null;
    }

    @Override
    public String getResourcePath() {
        return RESOURCE_PATH;
    }
}