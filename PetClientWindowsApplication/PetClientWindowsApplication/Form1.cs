using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PetClientWindowsApplication
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string endpoint = "http://localhost:8080/PetRESTService/rest/petservice/pet/count";
            RESTClient client = new RESTClient(endpoint, HttpVerb.GET, "1");

            int result = client.MakeRequest();

            textBox1.Text = Convert.ToString(result);
        }
    }
}
