const SignUpModal = () => {

    return (
        <>
            <form>
                <label>name</label>
                <input type="text" />
                <label>email</label>
                <input type="email" />
                <label>password</label>
                <input type="password" />
                <label>confirm password</label>
                <input type="password" />
                <label>zipcode</label>
                <input type="text" />
                <label>streetcode</label>
                <input type="text" />
                <label>house number</label>
                <input type="number" />
                <label>streetname</label>
                <input type="text" />
                <button type="submit"> sign up</button>
            </form>
        </>
    )
}

export default SignUpModal;