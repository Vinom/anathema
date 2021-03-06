package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IUniqueCharmType;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.lib.control.IChangeListener;

public class CharacterCharmModel {
  private ICharacter character;
  private MagicDescriptionProvider magicDescriptionProvider;

  public CharacterCharmModel(ICharacter character, MagicDescriptionProvider magicDescriptionProvider) {
    this.character = character;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public boolean isAllowedAlienCharms() {
    ICharmTemplate charmTemplate = character.getCharacterTemplate().getMagicTemplate().getCharmTemplate();
    return charmTemplate.isAllowedAlienCharms(getCaste().getType());
  }

  public IUniqueCharmType getUniqueCharmType() {
    ICharmTemplate charmTemplate = character.getCharacterTemplate().getMagicTemplate().getCharmTemplate();
    return charmTemplate.getUniqueCharmType();
  }

  public void addCasteChangeListener(IChangeListener listener) {
    ITypedDescription<ICasteType> caste = getCaste();
    caste.addChangeListener(listener);
  }

  public ICharmConfiguration getCharmConfiguration() {
    return character.getCharms();
  }

  private ITypedDescription<ICasteType> getCaste() {
    return character.getCharacterConcept().getCaste();
  }

  public void toggleLearned(String charmId) {
    ICharmConfiguration charms = getCharmConfiguration();
    ILearningCharmGroup charmGroup = getCharmGroupByCharmId(charmId);
    charmGroup.toggleLearned(charms.getCharmById(charmId));
  }

  private ILearningCharmGroup getCharmGroupByCharmId(String charmId) {
    ICharmConfiguration charms = getCharmConfiguration();
    ICharm charm = charms.getCharmById(charmId);
    return charms.getGroup(charm);
  }

  public boolean hasUniqueCharmType() {
    ICharmTemplate charmTemplate = character.getCharacterTemplate().getMagicTemplate().getCharmTemplate();
    return charmTemplate.hasUniqueCharms();
  }

  public MagicDescriptionProvider getMagicDescriptionProvider() {
    return magicDescriptionProvider;
  }
}
