package indivus.cosmos;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.widget.EditText;

/**
 * Created by seowo on 2017-07-07.
 */

public class Dialog {
    Context context;

    AlertDialog.Builder dialog;

    public Dialog(Context context) {
        this.context = context;
        dialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.dialog));
    }

    public void setDialog(String title, int line, final DialogCallBack callBack){
        dialog.setTitle(title);

        final EditText editText = new EditText(context);
        editText.setLines(line);
        editText.setHint("입력해주세요.");
        dialog.setView(editText);

        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callBack.finish(editText.getText().toString());
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    public void show(){
        dialog.show();
    }

    public interface DialogCallBack{
        public void finish(String edit);
    }
}
