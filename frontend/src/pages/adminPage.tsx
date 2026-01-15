import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import 'react-tabs/style/react-tabs.css';
import NewProductModal from "../components/modals/NewProductModal";
import UserList from '../components/UserList';
import ProductList from '../components/ProductList';



const AdminPage = () => {

    return (
        <>

  <Tabs>
    <TabList>
      <Tab>Products</Tab>
      <Tab>Users</Tab>
    </TabList>

    <TabPanel>
      <h2><NewProductModal />
      </h2>
      <ProductList/>
    </TabPanel>
    <TabPanel>
      <h2><UserList/></h2>
    </TabPanel>
  </Tabs>

            {/* <>
                <li>
                    username
                    email
                    customerId
                    <button>delete user</button>
                </li>
            </>
            <>
                <>
                    searchproduct
                    product list
                    <button>add new product</button>
                </>
                <li>
                    productname
                    price
                    stock
                    <button>edit product</button>
                </li>
                <br />
                    
            </> */}
        </>
    )
}

export default AdminPage;