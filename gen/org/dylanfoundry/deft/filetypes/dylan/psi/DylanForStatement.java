// This is a generated file. Not intended for manual editing.
package org.dylanfoundry.deft.filetypes.dylan.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DylanForStatement extends DylanStatement {

  @Nullable
  DylanBody getBody();

  @Nullable
  DylanFinallyClause getFinallyClause();

  @NotNull
  DylanForClauses getForClauses();

  @NotNull
  DylanForStatementTail getForStatementTail();

}
