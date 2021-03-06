/*
 * Copyright 2013, Bruce Mitchener, Jr.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dylanfoundry.deft.filetypes.dylan.structure;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.dylanfoundry.deft.filetypes.dylan.psi.DylanDefinition;
import org.dylanfoundry.deft.filetypes.dylan.psi.DylanFile;
import org.dylanfoundry.deft.filetypes.dylan.psi.DylanSourceRecords;

import java.util.ArrayList;
import java.util.List;

public class DylanStructureViewElement implements StructureViewTreeElement, SortableTreeElement {
  private PsiElement element;

  public DylanStructureViewElement(PsiElement element) {
    this.element = element;
  }

  @Override
  public Object getValue() {
    return element;
  }

  @Override
  public void navigate(boolean requestFocus) {
    if (element instanceof NavigationItem) {
      ((NavigationItem) element).navigate(requestFocus);
    }
  }

  @Override
  public boolean canNavigate() {
    return element instanceof NavigationItem &&
          ((NavigationItem)element).canNavigate();
  }

  @Override
  public boolean canNavigateToSource() {
    return element instanceof NavigationItem &&
          ((NavigationItem)element).canNavigateToSource();
  }

  @Override
  public String getAlphaSortKey() {
    return element instanceof PsiNamedElement ? ((PsiNamedElement) element).getName() : null;
  }

  @Override
  public ItemPresentation getPresentation() {
    return element instanceof NavigationItem ?
          ((NavigationItem) element).getPresentation() : null;
  }

  @Override
  public TreeElement[] getChildren() {
    if (element instanceof DylanFile) {
      DylanSourceRecords sourceRecords = PsiTreeUtil.getChildOfType(element, DylanSourceRecords.class);
      List<DylanDefinition> definitions = PsiTreeUtil.getChildrenOfTypeAsList(sourceRecords.getBody(), DylanDefinition.class);
      if (!definitions.isEmpty()) {
        List<TreeElement> treeElements = new ArrayList<TreeElement>(definitions.size());
        for (DylanDefinition definition : definitions) {
          treeElements.add(new DylanStructureViewElement(definition));
        }
        return treeElements.toArray(new TreeElement[treeElements.size()]);
      } else {
        return EMPTY_ARRAY;
      }
    } else {
      return EMPTY_ARRAY;
    }
  }
}
