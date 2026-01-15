import { useMutation, useQueryClient } from "@tanstack/react-query"
import { useState } from "react"
import { Button, Card, Col, Container, Form, Row } from "react-bootstrap"
import type { productCreateDTO } from "../../types/form.types"
import CustomModal from "./CustomModal"
import { API_URL } from "../../App"

interface variant {
    key: number,
    variantAttributeOne: variantAttribute,
    variantAttributeTwo: variantAttribute,
    stock: number,
    price: number,
    imageURL: string
}

interface variantAttribute {
    attribute: string,
    value: string | undefined
    type: string
}

const NewProductModal = () => {

        // String productCode,
        // String defaultImageURL,
        // String category,
        // String name,
        // String description,
        // String productBrand,
        // List<ProductCreateDTO> products,
        // List<ProductAttributeCreateDTO> productAttributes

    const [formData, setFormData] = useState({
        defaultImageURL: '',
        name: '',
        productBrand: '',
        description: '',
        category: '',
        productAttributes: {},
        products: {}
    })

    const [show, setShow] = useState(false)

    const [products, setProducts] = useState<variant[]>([])

    const [variantOneData, setVariantOneData] = useState({
        attributeOne: "",
        valuesOne: ['']
    })

    const [variantTwoData, setVariantTwoData] = useState({
        attributeTwo: "",
        valuesTwo: ['']
    })

    const [numberOfProducts, setNumberOfProducts] = useState(1)
    const [numberOfproductAttributes, setNumberOfproductAttributes] = useState([1])

    // const [AttributeOnedisabled, setAttributeOneDisabled] = useState(false)
    // const [AttributeTwoDisabled, setAttributeTwoDisabled] = useState(false)

    const [variantAttributeOne, setVariantAttributeOne] = useState([0]);
    const [variantAttributeTwo, setVariantAttributeTwo] = useState([0]);

    const queryClient = useQueryClient();

    const createProduct = useMutation({
        mutationFn: async (FormData: productCreateDTO) => {
            const response = await fetch(`${API_URL}/products/create`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(FormData)
            });

            if (!response.ok) {
                throw new Error('Failed to create product')
            };

            return response.json();
        },

        onSuccess: (data) => {
            // eslint-disable-next-line @typescript-eslint/no-unused-expressions
            queryClient.invalidateQueries({ queryKey: ['products'] }),
                console.log('products created successfully', data),
                setFormData({
                    defaultImageURL: '',
                    name: '',
                    productBrand: '',
                    description: '',
                    category: '',
                    productAttributes: {},
                    products: {}
                });
        }
    })

    const addVariantAttributeOne = () => {
        setVariantAttributeOne([...variantAttributeOne, variantAttributeOne.length])
    }

    const addVariantAttributeTwo = () => {
        setVariantAttributeTwo([...variantAttributeTwo, variantAttributeTwo.length])
    }

    const removeVariantAttributeOne = () => {
        const length = variantAttributeOne.length
        const updatedList = variantAttributeOne.slice(0, (length - 1))
        setVariantAttributeOne(updatedList);
    }

    const removeVariantAttributeTwo = () => {
        const length = variantAttributeTwo.length
        const updatedList = variantAttributeTwo.slice(0, (length - 1))
        setVariantAttributeTwo(updatedList);
    }

    const addUniqueAttribute = () => {
        setNumberOfproductAttributes([...numberOfproductAttributes, numberOfproductAttributes.length + 1])
    }

    const removeUniqueAttribute = () => {
        const length = numberOfproductAttributes.length
        const updatedLength = numberOfproductAttributes.slice(0, (length - 1))
        setNumberOfproductAttributes(updatedLength)
    }

    // console.log("number of unique attributes", numberOfproductAttributes)

    const addAttributeOne = (eventAttributeOne) => {
        eventAttributeOne.preventDefault()
        // setAttributeOneDisabled(true)
        const attributeOneData = new FormData(eventAttributeOne.target);
        const data = Object.fromEntries(attributeOneData.entries());
        const attributeOne = data.attribute as string
        delete data.attribute;
        const valuesAttributeOne = Object.values(data)

        setVariantOneData({ attributeOne: attributeOne, valuesOne: valuesAttributeOne, type: "VARIANT" })
    }


    const addAttributeTwo = (eventAttributeTwo) => {
        eventAttributeTwo.preventDefault()
        const attributeTwoDataRaw = new FormData(eventAttributeTwo.target);
        const attributeTwodata = Object.fromEntries(attributeTwoDataRaw.entries());
        const attributeTwo = attributeTwodata.attribute as string
        delete attributeTwodata.attribute;
        const valuesAttributeTwo = Object.values(attributeTwodata)

        setVariantTwoData({ attributeTwo: attributeTwo, valuesTwo: valuesAttributeTwo, type: "VARIANT" })
    }

    const changeNumberOfProducts = (eventNumberOfProducts: { target: { value: string } }) => {
        setNumberOfProducts(parseInt(eventNumberOfProducts.target.value))
    }

    let variantTwoHidden = true
    if (numberOfProducts === 2) { variantTwoHidden = false }
    else variantTwoHidden = true

    const updateVariantTable = () => {
        const productsArray: variant[] = [];
        let number = 0
        for (let i = 0; i < variantOneData.valuesOne.length; i++) {
            for (let j = 0; j < variantTwoData.valuesTwo.length; j++) {
                productsArray.push({key: number, variantAttributeOne: { attribute: variantOneData.attributeOne, value: variantOneData.valuesOne.at(i), type: "VARIANT" }, variantAttributeTwo: { attribute: variantTwoData.attributeTwo, value: variantTwoData.valuesTwo.at(j), type: "VARIANT"}, stock: NaN, price: NaN, imageURL: '' })
                number = number + 1
            }
        }
        setProducts(productsArray)
        console.log(products[1])
    }

    const handleChange = (key: number, event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        const updatedProducts: variant[] = [...products];
        updatedProducts[key] = { ...products[key], [name]: value }
        console.log(updatedProducts)
        setProducts(updatedProducts);
        setFormData({...formData, products:updatedProducts})
    }
    // console.log(products)

    const handleChangeBaseProduct = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value })
    }

    const lockInproductAttributes = (lockInEvent) => {
        lockInEvent.preventDefault()
        
        const productAttributesData = new FormData(lockInEvent.target)
        const data = Object.fromEntries(productAttributesData.entries())
        const values = Object.values(data)

        const productAttributesArray = []
        for (let i = 0; i < ((Object.values(data).length/2)); i++) {
            productAttributesArray.push({attribute: values[i*2], value: values[i*2+1], type: "PRODUCT"})
        }
        console.log(productAttributesArray)

        setFormData({ ...formData, productAttributes: productAttributesArray })
    }

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        createProduct.mutate(formData);
    }

    // const showFormdata = ()=>{
    //     console.log(formData)
    //     console.log(JSON.stringify(formData))
    // }

    return (
        <div>
            <Button as="input" variant="primary" defaultValue="create new product" onClick={() => setShow(true)}/>
            <CustomModal show={show} handleSubmit={() => handleSubmit} setShow={() => setShow(false)} title="new product">
                <Form>
                    {/* style={{all: "unset"}} */}
                    <Card > {/* all:unset stelt dat alle voorgaande CSS op default values word gereset */}
                        <Form.Group className="mb-3">
                            <Form.Label>product name</Form.Label>
                            <Form.Control placeholder="basic t-shirt" value={formData.name} type="text" id="name" name="name" onChange={() => handleChangeBaseProduct(event)} />

                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>product brand</Form.Label>
                            <Form.Control placeholder="nike" value={formData.productBrand} id="productBrand" name="productBrand" onChange={() => handleChangeBaseProduct(event)} />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>product description</Form.Label>
                            <Form.Control as={"textarea"} rows={3} value={formData.description} name="description" placeholder="basic t-shirt" onChange={() => handleChangeBaseProduct(event)} />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>category</Form.Label>
                            <Form.Control placeholder="type of product" type="text" value={formData.category} name="category" onChange={() => handleChangeBaseProduct(event)}>
                                {/* <option hidden value="">select a type</option>
                                <option>top</option>
                                <option>bottom</option>
                                <option>shoes</option>
                                <option>accessories</option> */}
                            </Form.Control>
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>base product image URL</Form.Label>
                            <Form.Control placeholder="URL" value={formData.defaultImageURL} name="defaultImageURL" onChange={() => handleChangeBaseProduct(event)} />
                        </Form.Group>
                    </Card>
                </Form>

                <Form onSubmit={lockInproductAttributes}>
                    <Card>
                        <Form.Group className="mb-3" >
                            <Form.Label>Unique attribute</Form.Label>
                            {/* {console.log(numberOfproductAttributes)} */}
                            {numberOfproductAttributes.map((index) => (
                                <Row key={index}>
                                    <Col> Attribute:<Form.Control placeholder="Attribute (eg. material)" type="text" name={"attribute" + index} /></Col>
                                    <Col> Value:<Form.Control placeholder="Value (eg. cotton)" type="text" name={"value" + index} /> </Col>
                                    <Col> <br />
                                    </Col> </Row>
                            ))}
                            <Button variant="primary" onClick={addUniqueAttribute}>Add unique attribute</Button>
                            <Button variant="primary" onClick={removeUniqueAttribute}>remove unique attribute</Button>
                        </Form.Group>
                        <br />
                        <Button type="submit" variant="primary">lock in these unique attribute</Button>
                    </Card>
                </Form>

                select number of variant attributes
                <br />
                <select value={numberOfProducts} onChange={changeNumberOfProducts}>
                    <option value="1" >One</option>
                    <option value="2" >Two</option>
                </select>
                <Card>
                    <Container style={{ border: "1px, solid, light gray" }}>
                        <Row style={{ display: "flex", justifyContent: "center" }}>
                            <Col>
                                <Form onSubmit={addAttributeOne}>
                                    <Form.Group className="mb-3">
                                        <Col>
                                            <Form.Label>Variant attribute one</Form.Label>
                                            <Col>
                                                <Form.Control placeholder="Attribute (eg. size)" type="text" name="attribute" /></Col>

                                            <div>
                                                Values
                                                {variantAttributeOne.map((index) => (
                                                    <Col key={index}>
                                                        <Form.Control placeholder="Value (eg. large)" type="text" name={"value" + index} />
                                                    </Col>
                                                ))}
                                            </div>
                                            <Row>
                                                <Col> <Button variant="primary" onClick={addVariantAttributeOne}> Add value </Button> </Col>
                                                <Col> <Button variant="primary" onClick={removeVariantAttributeOne}> Delete value </Button> </Col>
                                            </Row>

                                            <br />

                                            <Button type="submit" variant="primary"> add this variant attribute </Button>

                                        </Col>
                                    </Form.Group>
                                </Form>
                            </Col>
                            <Col hidden={variantTwoHidden}>
                                <Form onSubmit={addAttributeTwo}>
                                    <Form.Group className="mb-3">
                                        <Form.Label >Variant attribute two</Form.Label>
                                        <Col>
                                            <Form.Control placeholder="Attribute (eg. color)" type="text" name="attribute" />
                                        </Col>

                                        <div>
                                            Values
                                            {variantAttributeTwo.map((index) => (
                                                <Col key={index}>
                                                    <Form.Control placeholder="Value (eg. blue)" type="text" name={"value" + index} />
                                                </Col>
                                            ))}
                                        </div>
                                        <Row>
                                            <Col> <Button variant="primary" onClick={addVariantAttributeTwo}> Add value </Button> </Col>
                                            <Col> <Button variant="primary" onClick={removeVariantAttributeTwo}> Delete value </Button> </Col>
                                        </Row>

                                        <br />

                                        <Button type="submit" variant="primary"> add this variant attribute </Button>
                                    </Form.Group>
                                </Form>
                            </Col>
                        </Row>
                    </Container>
                </Card >

                <Button type="button" variant="primary" onClick={updateVariantTable}> update table </Button>


                {products.map((variant) => (
                    <div key={variant.key}>
                        {variant.variantAttributeOne.attribute}: {variant.variantAttributeOne.value} {variant.variantAttributeTwo.attribute}: {variant.variantAttributeTwo.value}
                        <label htmlFor=" price"></label>
                        <input placeholder="price" type="number" name="price" onChange={() => handleChange(variant.key, event)} />
                        <label htmlFor="stock"></label>
                        <input placeholder="stock" type="number" name="stock" onChange={() => handleChange(variant.key, event)} />
                        <label htmlFor="imageURL"></label>
                        <input placeholder="image url" type="text" name="imageURL" onChange={() => handleChange(variant.key, event)} />
                        {/* <Button onClick={updateVariant(variant.key, variant.price, variant.stock, variant.imageURL)}> add</Button> */}
                    </div>
                ))}

                <br />

                <Form onSubmit={() => handleSubmit}>
                    <Button type="submit" variant="primary">submit product and variants</Button>
                </Form>
                    {/* <Button variant="primary" onClick={showFormdata}>show formdata</Button> */}

            </CustomModal>
        </div>
    )
}
export default NewProductModal