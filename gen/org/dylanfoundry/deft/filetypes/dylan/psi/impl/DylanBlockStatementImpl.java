// This is a generated file. Not intended for manual editing.
package org.dylanfoundry.deft.filetypes.dylan.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.dylanfoundry.deft.filetypes.dylan.psi.DylanTypes.*;
import org.dylanfoundry.deft.filetypes.dylan.psi.*;

public class DylanBlockStatementImpl extends DylanStatementImpl implements DylanBlockStatement {

  public DylanBlockStatementImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public DylanBlockTail getBlockTail() {
    return findChildByClass(DylanBlockTail.class);
  }

  @Override
  @Nullable
  public DylanBody getBody() {
    return findChildByClass(DylanBody.class);
  }

  @Override
  @NotNull
  public List<DylanStatement> getStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DylanStatement.class);
  }

  @Override
  @Nullable
  public DylanVariableName getVariableName() {
    return findChildByClass(DylanVariableName.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DylanVisitor) ((DylanVisitor)visitor).visitBlockStatement(this);
    else super.accept(visitor);
  }

}
