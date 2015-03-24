package visit.ez.wyse.ezvisit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import Sqlite_Data.Data_DirrecionTemp;
import Sqlite_Data.SQL_ClientTipos;
import Sqlite_Data.SQL_DirrecionTemp;
import Sqlite_Data.SQL_TipoAddresses;


public class AnadirDir extends ActionBarActivity {

    public SQL_TipoAddresses myAddr;
    public SQL_DirrecionTemp SqlDirTemp;
    public Context _Con;
    Spinner spTipoDir;
    EditText editDir;
    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_direccion);

        _Con = this;
        SqlDirTemp = new SQL_DirrecionTemp(_Con);

        myList = (ListView)findViewById(R.id.listViiewDir);
        myList.setAdapter(SqlDirTemp.ShowDirTemp(_Con));

        myAddr = new SQL_TipoAddresses(this);

        // Getting the objects
        spTipoDir = (Spinner)findViewById(R.id.spinTipoDir);
        spTipoDir.setAdapter(myAddr.ListaTipos(_Con));

        editDir= (EditText)findViewById(R.id.editDir);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_anadir_dir, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void AddDir(View v)
    {

        Data_DirrecionTemp myDirTemp = new Data_DirrecionTemp();
        myDirTemp.Direccion = editDir.getText().toString();
        myDirTemp.DirTipo = myAddr.getTipoID(spTipoDir.getSelectedItem().toString());

        SqlDirTemp.saveRecord(myDirTemp);

        myList.setAdapter(SqlDirTemp.ShowDirTemp(_Con));

    }

    public void CancelarDir(View v)
    {
        finish();
    }

    public void SalvarDir(View v)
    {
        finish();
    }
}
