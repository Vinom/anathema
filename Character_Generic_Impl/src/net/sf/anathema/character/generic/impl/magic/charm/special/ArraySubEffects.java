package net.sf.anathema.character.generic.impl.magic.charm.special;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.generic.magic.charms.special.SubEffects;
import net.sf.anathema.lib.collection.ArrayUtilities;
import net.sf.anathema.lib.util.IPredicate;

import java.util.Iterator;

public class ArraySubEffects implements SubEffects{
  private final ISubeffect[] subeffects;

  public ArraySubEffects(ISubeffect[] subeffects) {
    this.subeffects = subeffects;
  }

  @Override
  public ISubeffect[] getEffects() {
    return subeffects;
  }

  @Override
  public ISubeffect getById(final String id) {
    return ArrayUtilities.getFirst(subeffects, new IPredicate<ISubeffect>() {
      @Override
      public boolean evaluate(ISubeffect input) {
        return input.getId().equals(id);
      }
    });
  }

  @Override
  public Iterator<ISubeffect> iterator() {
    return Lists.newArrayList(subeffects).iterator();
  }
}