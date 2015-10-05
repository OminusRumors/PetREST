﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Net;

public enum HttpVerb
{
    GET,
    POST,
    PUT,
    DELETE
}

namespace PetClientWindowsApplication
{
    class RESTClient
    {
        public string EndPoint { get; set; }
        public HttpVerb Method { get; set; }
        public string ContentType { get; set; }
        public string PostData { get; set; }

        public RESTClient(string endpoint, HttpVerb method, string postData)
        {
            EndPoint = endpoint;
            Method = method;
            ContentType = "text/xml";
            PostData = postData;
        }

        //The HttpWebRequest object in the System.Net namespace does all the heavy lifting. It makes the web request and 
        //has properties to defign how you want the request submitted across the web. From there I'm reading the response
        //stream and returning it. The rest is just basic class design used to encapselate the functionality into a reusable component. 
        public int MakeRequest()
        {
            var request = (HttpWebRequest)WebRequest.Create(EndPoint);

            request.Method = Method.ToString();
            request.ContentLength = 0;
            request.ContentType = ContentType;

            if (!string.IsNullOrEmpty(PostData) && Method == HttpVerb.POST)
            {
                var encoding = new UTF8Encoding();
                var bytes = Encoding.GetEncoding("iso-8859-1").GetBytes(PostData);
                request.ContentLength = bytes.Length;

                using (var writeStream = request.GetRequestStream())
                {
                    writeStream.Write(bytes, 0, bytes.Length);
                }
            }

            using (var response = (HttpWebResponse)request.GetResponse())
            {
                var responseValue = 0;

                if (response.StatusCode != HttpStatusCode.OK)
                {
                    //var message = String.Format("Request failed. Received HTTP {0}", response.StatusCode);
                    //throw new ApplicationException(message);
                    return -1;
                }

                // grab the response
                using (var responseStream = response.GetResponseStream())
                {
                    if (responseStream != null)
                        using (var reader = new StreamReader(responseStream))
                        {
                            responseValue = reader.Read();
                        }
                }

                return responseValue;
            }
        }
    }
}
