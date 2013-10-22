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

public class DylanIfStatementImpl extends DylanStatementImpl implements DylanIfStatement {

  public DylanIfStatementImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public DylanBody getBody() {
    return findChildByClass(DylanBody.class);
  }

  @Override
  @Nullable
  public DylanExpression getExpression() {
    return findChildByClass(DylanExpression.class);
  }

  @Override
  @Nullable
  public DylanIfTail getIfTail() {
    return findChildByClass(DylanIfTail.class);
  }

  @Override
  @NotNull
  public List<DylanStatement> getStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DylanStatement.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DylanVisitor) ((DylanVisitor)visitor).visitIfStatement(this);
    else super.accept(visitor);
  }

}
