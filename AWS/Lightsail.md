# AWS vs. AWS Lightsail

| Feature             | **AWS EC2**                                    | **AWS Lightsail**                           |
| ------------------- | ---------------------------------------------- | ------------------------------------------- |
| **Complexity**      | High – powerful but more complex setup         | Simple – designed for ease of use           |
| **Pricing**         | Pay-as-you-go, can get expensive               | Fixed monthly pricing                       |
| **Scaling**         | Highly scalable (auto-scaling, load balancing) | Basic scaling options                       |
| **Services Access** | Full access to all AWS services                | Limited access to AWS services              |
| **Use Case**        | Enterprises, custom infrastructure             | Small apps, blogs, dev/testing environments |

⚙️ Create an Instance
Click “Create instance”.

Choose your platform:

Usually Linux/Unix (or Windows if needed).

Choose a blueprint (OS or App):

OS only (e.g., Ubuntu, Debian, CentOS)

App+OS (e.g., WordPress, LAMP, Node.js, etc.)

(Optional) Choose your instance plan:

Fixed-price monthly bundles, e.g.:

$3.50/month (512MB RAM)

$5/month (1GB RAM)

$10/month (2GB RAM)

Name your instance, or use the default.

Click “Create instance”.

🌐 Connect to Your Instance
Once the instance is running:

Click on it in your Lightsail dashboard.

Click “Connect using SSH” (browser-based terminal opens).

You're now inside your server!

Upload files:

Use SFTP (e.g., with FileZilla) using your instance’s public IP and private key.

Or use scp (secure copy) from local machine.

🌍 Configure DNS (Optional)
If you have a custom domain:

Go to the Networking tab in Lightsail.

Create a Static IP and attach it to your instance.

Set your domain’s A record to this static IP.
