package eu.qped.java.checkers.semantics;

import java.util.ArrayList;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class RecursiveCheckHelper {

    private final BlockStmt methodBody;
    private final ArrayList<String> calledMethods;

    private RecursiveCheckHelper(BlockStmt methodBody) {
        this.methodBody = methodBody;
        this.calledMethods = new ArrayList<>();
        walk();
    }

    public static RecursiveCheckHelper createRecursiveCheckHelper(BlockStmt methodBody){
        return new RecursiveCheckHelper(methodBody);
    }

    public boolean check(String methodName){
        return calledMethods.contains(methodName);
    }

    private void walk(){
        if (methodBody.getChildNodes() != null){
            methodBody.accept(new VoidVisitorAdapter<Void>() {

                @Override
                public void visit(MethodCallExpr n, Void arg){
                    super.visit(n, arg);
                    calledMethods.add(n.getName().toString());
                }
            } , null);
        }
    }
}
