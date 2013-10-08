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

package org.dylanfoundry.deft.filetypes.dylan;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import org.dylanfoundry.deft.filetypes.dylan.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DylanClassContributor implements ChooseByNameContributor {
  @NotNull
  @Override
  public String[] getNames(Project project, boolean includeNonProjectItems) {
    List<DylanDefiner> definitions = findClassDefinitions(project);
    List<String> names = new ArrayList<String>(definitions.size());
    for (DylanDefiner definition : definitions) {
      if (definition.getName() != null && definition.getName().length() > 0) {
        names.add(definition.getNameIdentifier().getText());
      }
    }
    return names.toArray(new String[names.size()]);
  }

  @NotNull
  @Override
  public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
    // todo include non project items
    List<DylanDefiner> definitions = findClassDefinitions(project, name);
    return definitions.toArray(new NavigationItem[definitions.size()]);
  }

  public static List<DylanDefiner> findClassDefinitions(Project project, String name) {
    List<DylanDefiner> result = null;
    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, DylanFileType.INSTANCE,
            GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      DylanFile dylanFile = (DylanFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (dylanFile != null) {
        DylanSourceRecords sourceRecords = PsiTreeUtil.getChildOfType(dylanFile, DylanSourceRecords.class);
        if (sourceRecords != null) {
          DylanDefinition[] definitions = PsiTreeUtil.getChildrenOfType(sourceRecords, DylanDefinition.class);
          if (definitions != null) {
            for (DylanDefinition definition : definitions) {
              DylanDefiner definer = PsiTreeUtil.getChildOfType(definition, DylanDefinitionClassDefiner.class);
              if (definer != null) {
                if (name.equals(definer.getNameIdentifier().getText())) {
                  if (result == null) {
                    result = new ArrayList<DylanDefiner>();
                  }
                  result.add(definer);
                }
              }
            }
          }
        }
      }
    }
    return result != null ? result : Collections.<DylanDefiner>emptyList();
  }

  public static List<DylanDefiner> findClassDefinitions(Project project) {
    List<DylanDefiner> result = new ArrayList<DylanDefiner>();
    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, DylanFileType.INSTANCE,
            GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      DylanFile dylanFile = (DylanFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (dylanFile != null) {
        DylanSourceRecords sourceRecords = PsiTreeUtil.getChildOfType(dylanFile, DylanSourceRecords.class);
        if (sourceRecords != null) {
          DylanDefinition[] definitions = PsiTreeUtil.getChildrenOfType(sourceRecords, DylanDefinition.class);
          if (definitions != null) {
            for (DylanDefinition definition : definitions) {
              DylanDefiner definer = PsiTreeUtil.getChildOfType(definition, DylanDefinitionClassDefiner.class);
              if (definer != null) {
                if (result == null) {
                  result = new ArrayList<DylanDefiner>();
                }
                result.add(definer);
              }
            }
          }
        }
      }
    }
    return result;
  }
}